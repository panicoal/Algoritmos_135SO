class proceso:
    def __init__(self, id, ll, cpu):
        self.id = id
        self.ll = ll
        self.cpu = cpu
        self.ini = 0
        self.fin = 0
        self.t = 0
        self.e = 0

    def calcular_tiempos(self, tiempo_actual):
        self.ini = max(self.ll, tiempo_actual)
        self.fin = self.ini + self.cpu
        self.t = self.fin - self.ll
        self.e = self.t - self.cpu
        return self.fin

    def mostrar(self):
        print(f"{self.id} \t {self.ll} \t {self.cpu} \t {self.ini} \t {self.fin} \t {self.t} \t {self.e}")


num = int(input("#Procesos : "))
cola = []

for i in range(num):
    print(f'Proceso {i}')
    tll, tcpu = map(int, input("tll tcpu\n").split())
    cola.append(proceso(i, tll, tcpu))
    print("\n")

# Ordenar la lista por tiempo de llegada (FIFO)
cola.sort(key=lambda p: p.ll)

# Calcular tiempos
tiempo_actual = 0
promedio_ttotal = 0
promedio_tespera = 0

print("id \t tll \t tcpu \t ini \t fin \t t \t e")
for proceso in cola:
    tiempo_actual = proceso.calcular_tiempos(tiempo_actual)
    proceso.mostrar()
    promedio_ttotal += proceso.t
    promedio_tespera += proceso.e

# Mostrar promedios
print(f"Promedio_ttotal: {promedio_ttotal / num:.2f}")
print(f"Promedio_tespera: {promedio_tespera / num:.2f}")
