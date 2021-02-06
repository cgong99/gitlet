# gitlet
This is a Berkeley CS61B's Final Project.
Gitlet is a version-control system that mimics some of the basic features of the popular system Git.
see the original project description: https://inst.eecs.berkeley.edu/~cs61b/sp20/materials/proj/proj3/index.html

### The Commands
#### init
Usage: java gitlet.Main init
Description: Creates a new Gitlet version-control system in the current directory. This system will automatically start with one commit: a commit that contains no files and has the commit message initial commit (just like that, with no punctuation). It will have a single branch: master, which initially points to this initial commit, and master will be the current branch. The timestamp for this initial commit will be 00:00:00 UTC, Thursday, 1 January 1970 in whatever format you choose for dates (this is called "The (Unix) Epoch", represented internally by the time 0.) Since the initial commit in all repositories created by Gitlet will have exactly the same content, it follows that all repositories will automatically share this commit (they will all have the same UID) and all commits in all repositories will trace back to it.
Runtime: Should be constant relative to any significant measure.
Failure cases: If there is already a Gitlet version-control system in the current directory, it should abort. It should NOT overwrite the existing system with a new one. Should print the error message A Gitlet version-control system already exists in the current directory.
Dangerous?: No
Our line count: ~25
#### add
Usage: java gitlet.Main add [file name]
Description: Adds a copy of the file as it currently exists to the staging area (see the description of the commit command). For this reason, adding a file is also called staging the file for addition. Staging an already-staged file overwrites the previous entry in the staging area with the new contents. The staging area should be somewhere in .gitlet. If the current working version of the file is identical to the version in the current commit, do not stage it to be added, and remove it from the staging area if it is already there (as can happen when a file is changed, added, and then changed back). The file will no longer be staged for removal (see gitlet rm), if it was at the time of the command.
Failure cases: If the file does not exist, print the error message File does not exist. and exit without changing anything.
Dangerous?: No
Our line count: ~20
#### commit
Usage: java gitlet.Main commit [message]
Description: Saves a snapshot of certain files in the current commit and staging area so they can be restored at a later time, creating a new commit. The commit is said to be tracking the saved files. By default, each commit's snapshot of files will be exactly the same as its parent commit's snapshot of files; it will keep versions of files exactly as they are, and not update them. A commit will only update the contents of files it is tracking that have been staged for addition at the time of commit, in which case the commit will now include the version of the file that was staged instead of the version it got from its parent. A commit will save and start tracking any files that were staged for addition but weren't tracked by its parent. Finally, files tracked in the current commit may be untracked in the new commit as a result being staged for removal by the rm command (below).

The bottom line: By default a commit is the same as its parent. Files staged for addition and removal are the updates to the commit.

#### global-log
Usage: java gitlet.Main global-log
Description: Like log, except displays information about all commits ever made. The order of the commits does not matter.
Runtime: Linear with respect to the number of commits ever made.
Failure cases: None
Dangerous?: No
Our line count: ~10

#### status
Usage: java gitlet.Main status
Description: Displays what branches currently exist, and marks the current branch with a *. Also displays what files have been staged for addition or removal. An example of the exact format it should follow is as follows.

=== Branches ===
*master
other-branch

=== Staged Files ===
wug.txt
wug2.txt

=== Removed Files ===
goodbye.txt

=== Modifications Not Staged For Commit ===
junk.txt (deleted)
wug3.txt (modified)

=== Untracked Files ===
random.stuff
There is an empty line between sections. Entries should be listed in lexicographic order, using the Java string-comparison order (the asterisk doesn't count). A file in the working directory is "modified but not staged" if it is

