import csv

csv_read1 = csv.reader(file('compute1.csv'))
csv_read2 = csv.reader(file('compute2.csv'))
default = [10, 20]
dim = [40, 30]

count = 0
count1=[]
print(count1)

for row in csv_read1:
	print("Computing the counts....")
	if float(row[1]) - default[0] <= dim[0] and float(row[2]) - default[1] <= dim[1]:
		csv_read2 = csv.reader(file('compute1.csv'))
		for row1 in csv_read2:
			if row1[0] == row[0]:
				count = count + 1
			else:
				count1 += [[row1[0], count]]
				count = 0
count2 = []
for i in range(len(count1)):
	if count1[i][1] != 0:
		count2 += [count1[i]]
print(count2)

csv_write1 = csv.writer(file('updateCount.csv', 'wb'), delimiter=',',
                            quotechar='"', quoting=csv.QUOTE_MINIMAL)			
for row in count2:
	print("Writing data into a file to upload.....")
	csv_write1.writerow([unicode(col) for col in row])
