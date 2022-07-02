import sys
# author Matthew Jennings

valid = list()
file = open(sys.argv[1], 'r')
words = file.read()
swords = words.split()

for word in swords:
    word.rstrip(' ')
    word.lstrip(' ')
    if(word[0] == '"' and len(word) > 1):
        word = word[1:len(word)]
    if(word[-1] == '"' and len(word) > 1):
        word = word[0:-1]
    if(word[-1] in ".,;:?!" and len(word) > 1):
        word = word[0:-1]
    if(word[0].isupper):
        word = word[0].lower() + word[1:len(word)]
    if('\'' in word[1:-1]):
        apos = word.index('\'')
        word = word[0:apos] + word[apos+1:len(word)]
    else:
        apos = 0
    if(word.islower() & word.isalpha()):
        if(apos != 0):
            word = word[0:apos] + "\'" + word[apos:len(word)]
        if(word not in valid):
            valid.append(word)

valid.sort()

for i in valid:
    print(i)
