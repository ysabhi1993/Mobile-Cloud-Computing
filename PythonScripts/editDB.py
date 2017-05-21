import csv
import MySQLdb
 
mydb = MySQLdb.connect(host='35.190.131.51',
    user='root',
    passwd='YSabhi_1993!',
    db='MCCFinalProject')
cursor = mydb.cursor()

#cursor.execute("CREATE TABLE MCCFinalProject.event_details (email VARCHAR(40), imei_No VARCHAR(20), passwd VARCHAR(25), event_Name VARCHAR(25),  latitude FLOAT(25,15), longitude FLOAT(25,15));")

#cursor.execute("Drop table if exists MCCFinalProject.event_details;")
csv_data = csv.reader(file('insert.csv'))
for row in csv_data:
	print("Loading data into event_details table.....")
	cursor.execute("INSERT INTO MCCFinalProject.event_details(event_Name, passwd, email, imei_No) VALUES(%s, %s, %s, %s)", row[2:])

csv_data = csv.reader(file('update.csv'))
for row in csv_data:
	print("Loading data into Location table.....")
        cursor.execute("INSERT INTO MCCFinalProject.Location(latitude, longitude, email) VALUES(%s, %s, %s)", row)

#close the connection to the database.




mydb.commit()
cursor.close()
