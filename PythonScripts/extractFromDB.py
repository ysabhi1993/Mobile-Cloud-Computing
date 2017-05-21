import csv
import MySQLdb

mydb = MySQLdb.connect(host='35.190.131.51',
    user='root',
    passwd='YSabhi_1993!',
    db='MCCFinalProject')
cursor = mydb.cursor()
cursor.execute("select email, latitude, longitude from MCCFinalProject.Location order by 1;")

result=cursor.fetchall()

csv_write1 = csv.writer(file('compute1.csv', 'wb'), delimiter=',',
                            quotechar='"', quoting=csv.QUOTE_MINIMAL)
for row in result:
        csv_write1.writerow([unicode(col) for col in row])


cursor.execute("select email, count from MCCFinalProject.event_details order by 1;")

result2 = cursor.fetchall()
csv_write2 = csv.writer(file('compute2.csv', 'wb'), delimiter=',',
                            quotechar='"', quoting=csv.QUOTE_MINIMAL)

for row in result2:
	print("writing data from database into files.....")
        csv_write2.writerow([unicode(col) for col in row])


cursor.execute("select * from MCCFinalProject.event_details order by 1;")

result3 = cursor.fetchall()
csv_write3 = csv.writer(file('result.csv', 'wb'), delimiter=',',
                            quotechar='"', quoting=csv.QUOTE_MINIMAL)

for row in result3:
	print("writing data from database into files.....")
        csv_write3.writerow([unicode(col) for col in row])

print("uploading file into Google Cloud bucket.....")

