import threading
import time

flag=[False, False]
turno = 0 
N = 3

def dekker(i):
    global flag, turno
    j = 1 - i
    for n in range (N):
        print(f"Proceso {i}: quiere entrar a SC")
        flag[i] = True
        while flag[j]:
            if turno ==i:
                time.sleep(1)
                continue
            flag[i]= False
            while turno == j:
                time.sleep(0)
            flag[i] =True
        print(f"proceso {i}: entrando a SC (iteracion {n+1})")
        time.sleep(1)
        print(f"proceso {i}: saliendo de SC)")
        turno = j
        flag[i]=False
        time.sleep(1)

def peterson(i):
    global flag, turno
    j = 1 - i 
    for n in range(N):
        print(f"Proceso {i}: quiere entrar a la SC")  # LOCK
        flag[i] = True  
        turno = j       
        while flag[j] and turno == j:
            pass

        print(f"Proceso {i}: entrando a la SC (iteración {n+1})")
        time.sleep(1)

        print(f"Proceso {i}: saliendo de la SC")  # UNLOCK
        flag[i] = False
        time.sleep(1) 

#Main
p0 = threading.Thread(target = dekker, args=(0,))
p1 = threading.Thread(target = dekker, args=(1,))
p0.start()
p1.start()
p0.join()
p1.join()
print("exe terminada")