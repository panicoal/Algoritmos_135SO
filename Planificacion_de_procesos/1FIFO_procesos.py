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
        self.ini = max(self.ll, tiempo_actual)
        self.fin = self.ini + self.cpu
        self.t = self.fin - self.ll
        self.e = self.t - self.cpu
        self.p = round(self.t / self.cpu,2)
        self.i = round(self.cpu / self.t,2)
        return self.fin

    def mostrar(self):
        print(f"{self.id} \t {self.ll} \t {self.cpu} \t {self.ini} \t {self.fin} \t {self.t} \t {self.e} \t {self.p} \t {self.i}")

#Main
num = int(input("#Procesos : "))
cola = []
for i in range(num):
    print(f'Proceso {i+1}')
    tll, tcpu = map(int, input().split())
    cola.append(proceso(i+1, tll, tcpu))

cola.sort(key=lambda p: p.ll)

tiempo_actual = 0

prom_ttotal = 0
prom_tespera = 0
prom_ppena = 0
prom_pres = 0

print("\nP \t Tll \t Tcpu \t ini \t fin \t T \t E \t P \t I")
for proceso in cola:
    tiempo_actual = proceso.calcular(tiempo_actual)
    proceso.mostrar()
    prom_ttotal += proceso.t
    prom_tespera += proceso.e
    prom_ppena += proceso.p
    prom_pres += proceso.i

print(f"Promedio_ttotal: {prom_ttotal / num:.2f}")
print(f"Promedio_tespera: {prom_tespera / num:.2f}")
print(f"Promedio_tespera: {prom_ppena / num:.2f}")
print(f"Promedio_tespera: {prom_pres / num:.2f}")

'''
3
0 3
2 4
1 2

'''