def convert(filename):
    primitiveNoteFile = open(filename+".txt", "r")
    convertedNoteFile = open(filename+"_converted.txt", "w")
    noteList = primitiveNoteFile.readlines()
    
    for i in noteList:
        convertedNote = ""
        notes = i.split()
        #print(line+" "+timing)
        if notes[0] == "0":
            continue
        for k in range(1, 6):
            if str(k) == notes[0]:
                convertedNote += "1,"
            else:
                convertedNote += "0,"
        if "6" == notes[0]:
            convertedNote += "1"
        else:
            convertedNote += "0"

        convertedNote +="#"

        convertedNote += str(int((float(notes[1]) - 0.4)*1000))
        try:
            convertedNote += "#"+notes[2]
        except:
            convertedNote += "#"
        #print(convertedNote)
        convertedNoteFile.write(convertedNote+"\n")

    primitiveNoteFile.close()
    convertedNoteFile.close()
    print("done!")

def convert2(filename):
    primitiveNoteFile = open(filename+".txt", "r")
    convertedNoteFile = open(filename+"_converted.txt", "w")
    noteList = primitiveNoteFile.readlines()
    
    for i in noteList:
        convertedNote = ""
        notes = i.split()
        #print(line+" "+timing)
        if notes[0] == "0":
            continue
        for k in range(1, 6):
            if str(k) == notes[0]:
                convertedNote += "1,"
            else:
                convertedNote += "0,"
        if "6" == notes[0]:
            convertedNote += "1"
        else:
            convertedNote += "0"

        convertedNote +="#"

        convertedNote += str(int((float(notes[1]))))
        try:
            convertedNote += "#"+notes[2]
        except:
            convertedNote += "#"
        #print(convertedNote)
        convertedNoteFile.write(convertedNote+"\n")

    primitiveNoteFile.close()
    convertedNoteFile.close()
    print("done!")

def analysis(filename):
    primitiveNoteFile = open(filename+".txt", "r")
    convertedNoteFile = open(filename+"_analysis.txt", "w")
    
    noteList = primitiveNoteFile.readlines()
    
    for i in noteList:
        convertedNote = ""
        note = i.split()
        
        convertedNote += note[0]
        timing = round( (float(note[1]) - 0.6)*10 )*100

        convertedNote += " "+str(timing)
        convertedNoteFile.write(convertedNote+"\n")

    primitiveNoteFile.close()
    convertedNoteFile.close()
    print("done!")
    
    return filename+"_analysis"
filename = "music2"

convert(filename+"_")

