def convert():
    primitiveNoteFile = open("music2.txt", "r")
    convertedNoteFile = open("music3.txt", "w")
    noteList = primitiveNoteFile.readlines()
    
    for i in noteList:
        convertedNote = ""
        line, timing = i.split()
        #print(line+" "+timing)
        
        for k in range(1, 6):
            if str(k) == line:
                convertedNote += "1,"
            else:
                convertedNote += "0,"
        if "6" == line:
            convertedNote += "1"
        else:
            convertedNote += "0"

        convertedNote +="#"

        convertedNote += str((float(timing)-0.3)*1000)
        #print(convertedNote)
        convertedNoteFile.write(convertedNote+"\n")

    primitiveNoteFile.close()
    convertedNoteFile.close()
    print("done!")
convert()
