import psycopg2

conection=psycopg2.connect(
    host='localhost',
    user='postgres',
    password='admin',
    database='',
    port="5432"
)

cursor=conection.cursor()
sentencia = 'SELECT * FROM clientes'
cursor.execute(sentencia)
registro = cursor.fetchall()
print(registro)

def insert_data():

    cursor = conection.cursor()
        
    # Sentencia SQL para insertar datos
    #query = 
        
    # Ejecutar la sentencia
    cursor.execute()
        
    # Confirmar los cambios
    conection.commit()
    print("Datos insertados correctamente")
        
    
    cursor.close()
    conection.close()
    print("Conexión a PostgreSQL cerrada")