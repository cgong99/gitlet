# Gitlet Design Document

**Name**: Chen Gong

## Classes and Data Structures
### Main
This main class take in arguments from user and call the correspond method.
### Refs
Where branches and head pointer stores.

### Add
Where add command be executed.

### Commit

#### Instance Variables:
* message -  contains the message of a commit.
* parent -    store the pointer that points to the last commit.
* second Parent - for merge.
* timeStamp - represent the current time for this commit.
* A list of pointer that point to different blobs.
* Tree 

###Tree
Stores pointers to blobs.

### Repository
A class that read basic information from local repository. Other classes might
need to access data from this class.

### Stage
The space that stores changes to be commit next time.
#### Instance Variables:
* addition - a list of files that are added.
* removal - a list of files that are removed.

### Blob
A kind of structure that stores a file's different versions information, and 
the save file command conducted here.

### Merge 
A class that do the merge command.

### Tree
not sure yet.

## Algorithms
### Add:
1. Check if last commit contains this added file.
2. IF this is a new file, create a new directory for this file that
stores its blobs. Then create this version's blobs.
3. If this file already exits, check if this add is necessary.
4. Check if this blob equals to the last blob that stored in the last
commit.
5. If they are the same, return error with message.
6. Else, store the new blob.
###Commit:
For each commit time, we will create a new commit object according
to the current stage, time and message. 
1. We capture the current time
and the message from user, and record it in the new commit. 
2. Make the parent pointer point to the last commit.
3. For everything in the addition space, we will fist check if we 
have already have its pointer in the last commit.
4. If we already have it, then change it point to the new blob in this
commit.
5. else we creat a new pointer point to the blob.
6. For every thing in the removal sate, delete all the blobs for
this file and delete the pointer either.
7. Change the head pointer to this new commit object.
8. Write this commit to the commit folder and update a record
of the sequence of commit.
###Merge:
Not sure yet.

### Repository:
Init this repository file each time runs the program. Log the current
commit information and creat the old commit inside.

## Persistence
### Files or Subdirectories :
* HEAD - shows the current head pointer's place.
* Object - stores every things blob here. Include commit, tree, blobs.
* Commit - A directory stores the commit statues and a linked list of commit.
* Stage/index - Stores all the files in next commit. Addition stage and removal stage which stores the files 
that need to be commit. 
* refs - stores different branches and head pointers.
### Times that update repo
1. If some files are added through add command, check if their directory 
exit. If not create their directory and put their blobs inside. 
Also, creat objects and store it in the stage space with a pointer 
point to the current blob.
2. If commit are made, store the commit inside commit directory and
change the head pointer to the new commit. The parent pointer points
to the last commit. 
3. The staging area is cleared after a commit.
4. Merge
