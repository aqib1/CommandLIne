To run this application you can use, you can also build jar which is in libs folder.
or you can checkout code from github repository (i make it public)
and run that using eclipse IDE, you need to file name in jvm argument so that 
it can be fatched in main method.

if you want to run build jar.

java​ -cp ​TextSearch​.jar ​com.runner.pkg.MainClass​ <path of directory> 









Back-End Coding Exercise 

The exercise is to write a command line driven text search engine in the language you feel most
comfortable with.
Examples of the usage would be:
python simple_search.py <pathToDirectoryContainingTextFiles>

or

java -jar <jarName> <mainClassFile> <pathToDirectoryContainingTextFiles>
This should read all the text files in the given directory, building an in-memory representation of the files
and their contents, and then give a command prompt at which interactive searches can be performed.

An example session might look like:

$ java -jar SimpleSearch.jar Searcher /foo/bar

14 files read in directory /foo/bar

search> to be or not to be

file1.txt:100%

file2.txt:90%

search>

search> cats

no matches found

search> :quit
$
The search should take the words given on the prompt and return a list of the top 10 (maximum)
matching filenames in rank order, giving the rank score against each match.
Note:  We would like to see a working console application, please focus your attention on the search
algorithm and the basic console functionality (you can assume that the input strings are sane).

Ranking
● The rank score must be 100% if a file contains all the words
● It must be 0% if it contains none of the words
● It should be between 0 and 100 if it contains only some of the words, but the exact ranking
formula is up to you to choose and implement
