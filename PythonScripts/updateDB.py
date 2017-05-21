import csv 
import  MySQLdb

mydb = MySQLdb.connect(host='35.190.131.51',
    user='root',
    passwd='YSabhi_1993!',
    db='MCCFinalProject')
cursor = mydb.cursor()

csv_data = csv.reader(file('updateCount.csv'))
for row in csv_data:
	print("Updating database with count.....")
	row_temp = [row[1], row[0]]
	cursor.execute("update MCCFinalProject.event_details set count=%s where email=%s;", row_temp)
