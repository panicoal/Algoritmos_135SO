class proceso:
    def __init__(self, id, ll, cpu):
        self.id = id
        self.ll = ll
        self.cpu = cpu
        self.ini = 0
        self.fin = 0
        self.t = 0
        self.e = 0
        self.p = 0
        self.i = 0

    def calcular(self, tiempo_actual):
        self.ini = max(tiempo_actual, self.ll)
        self.fin = self.ini + self.cpu
        self.t = self.fin - self.ll
        self.e = self.t - self.cpu
        self.p = round(self.t / self.cpu,2)
        self.i = round(self.cpu /self.t,2)
    def mostrar(self):
        print(f"{self.id} \t {self.ll} \t {self.cpu} \t {self.ini} \t {self.fin} \t {self.t} \t {self.e} \t {self.p} \t {self.i}")

#Main
num = int(input("#Procesos: "))
cola = []

for i in range (num):
    print(f"Proceso: {i+1}")
    ll,cpu = map(int, input().split())
    cola.append(proceso(i+1, ll, cpu))

tiempo_actual = 0
procesos_completos= 0

prom_tt=0
prom_te=0
prom_pp=0
prom_pi=0

print("id \t ll \t cpu \t ini \t fin \t T \t E \t P \t I")
while procesos_completos < num:
    disponibles = [p for p in cola if p.ll <= tiempo_actual and p.fin == 0]
    if not disponibles:
        tiempo_actual += 1
        continue

    sig = min(disponibles, key=lambda p: p.cpu)
    sig.calcular(tiempo_actual)

    tiempo_actual = sig.fin
    procesos_completos += 1
    sig.mostrar()
    
    prom_tt += sig.t
    prom_te += sig.e
    prom_pp += sig.p
    prom_pi += sig.i

print(f"Prom Tiempo Total: {prom_tt / num:.2f}")
print(f"Prom Tiempo Espera: {prom_te / num:.2f}")
print(f"Prom Prop. Penalizacion: {prom_pp / num:.2f}")
print(f"Prom  Prop. Respuesta: {prom_pi / num:.2f}")


'''
4
0 6
1 8
2 7
3 3

'''