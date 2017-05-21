#this script differentiates between the data that has to be updated from the data that has to be inserted
#Data to be inserted has 6 fields. This data is loaded into insert.csv
#Data to be updated has 4 fields. This data is loaded into update.csv

import csv

with open('alldata.csv', 'rb') as f:
    reader = csv.reader(f)
    myList = list(reader)

myfile1 = open("insert.csv", "wb")
myfile2 = open("update.csv", "wb")

for i in myList:
	print("sorting into two files.....")
	if len(i) == 3:
		with open("update.csv","a") as myfile:
			w = csv.writer(myfile, quoting = csv.QUOTE_ALL)
			w.writerows([[i[2],i[0],i[1]]])
	else:
		with open("insert.csv","a") as myfile:
                        w = csv.writer(myfile, quoting = csv.QUOTE_ALL)
                        w.writerows([i])

