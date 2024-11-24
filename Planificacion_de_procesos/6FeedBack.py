class proceso:
    def __init__(self, id, ll, cpu, quantum):
        self.id = id
        self.ll = ll
        self.cpu = cpu
        self.cpu_rest = cpu  # CPU restante
        self.quantum = quantum  # Quantum inicial
        self.ini = 0
        self.fin = 0
        self.t = 0
        self.e = 0
        self.p = 0
        self.i = 0

    def calcular(self, tiempo_actual):
        self.ini = max(tiempo_actual, self.ll)
        self.fin = self.ini + self.cpu_rest
        self.t = self.fin - self.ll
        self.e = self.t - self.cpu
        self.p = round(self.t / self.cpu, 2)
        self.i = round(self.cpu / self.t, 2)

    def mostrar(self):
        print(f"{self.id} \t {self.ll} \t {self.cpu} \t {self.ini} \t {self.fin} \t {self.t} \t {self.e} \t {self.p} \t {self.i}")

#Main
num = int(input("#Procesos: "))
cola = []

# Input de los procesos
for i in range(num):
    print(f"Proceso: {i + 1}")
    ll, cpu = map(int, input().split())
    cola.append(proceso(i + 1, ll, cpu, quantum=4))  # Asignar un quantum inicial de 4

tiempo_actual = 0
procesos_completos = 0

prom_tt = 0
prom_te = 0
prom_pp = 0
prom_pi = 0

print("id \t ll \t cpu \t ini \t fin \t T \t E \t P \t I")

# Cola de procesos (simulando Round Robin con retroalimentación)
while procesos_completos < num:
    disponibles = [p for p in cola if p.ll <= tiempo_actual and p.cpu_rest > 0]
    
    if not disponibles:
        tiempo_actual += 1
        continue

    sig = disponibles.pop(0)  # Seleccionamos el siguiente proceso disponible
    quantum = sig.quantum

    if sig.cpu_rest <= quantum:
        tiempo_actual += sig.cpu_rest  # El proceso termina en esta ejecución
        sig.cpu_rest = 0
        sig.calcular(tiempo_actual)
        procesos_completos += 1
        sig.mostrar()
    else:
        sig.cpu_rest -= quantum  # El proceso no termina, se le resta el quantum
        tiempo_actual += quantum
        sig.quantum = min(sig.quantum * 2, 16)  # Aumenta el quantum hasta un máximo de 16
        cola.append(sig)  # El proceso vuelve al final de la cola

    prom_tt += sig.t
    prom_te += sig.e
    prom_pp += sig.p
    prom_pi += sig.i

# Cálculos de promedios
print(f"Prom Tiempo Total: {prom_tt / num:.2f}")
print(f"Prom Tiempo Espera: {prom_te / num:.2f}")
print(f"Prom Prop. Penalizacion: {prom_pp / num:.2f}")
print(f"Prom Prop. Respuesta: {prom_pi / num:.2f}")
