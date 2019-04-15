### Important note
Data file sentiment140.csv isn't included in the hw2_sentiment project due to the limit of size I was able to get uploaded on NYUclasses

### File preparation for Prince 
* Include a **run.sh** file that specifies the information below: 
    1. *load maven module* ``$ module load maven/3.5.2``
    1. *compile* 
        ``$ mvn compile``
        ``$ mvn exec:java -Dexec.mainClass="Analysis"``
        ``#SBATCH --mem=20GB`` (allow enough memory) 
* *load files to Prince using fugu* see "https://wikis.nyu.edu/display/NYUHPC/Submitting+jobs+with+sbatch"

### How to run on Prince
* **log onto Prince** 
    * ``$ ssh hpcgwtunnel``
    * ``$ ssh -Y prince``
* **request an interactive shell** ``$ srun --mem=5GB --time=00:15:00 --cpus-per-task 1 --pty $SHELL``

* **upload batch job** 
    * ``$cd hw2_sentiment``
    * ``$sbatch run.sh``
* **monitor job** ``squeue -u $USER``

### HPC setup
Stored in  ``./HPCsetup.png``

### Data exploration
* Top 20 most frequent @-mentions stored in ```./output/top@.txt```
* Top 20 most frequent hashtags stored in ``./output/topHashtags.txt``

### Keyword extraction
* textrank project separate from the main one above 
* run.sh is already included in folder "textrank". 
* Java module is specified to be reloaded in run.sh file as a response to Prince's persistence complaint on the wrong "JAVA_HOME" and not being able to locate java sdk. 
* To run the project on a local machine, use the following command:  ``ant -Ddata.file=test/<FROMFILE> run > <TOFILE>``
    where 
    * <FROMFILE> is amongst {text.txt, text0_tweet.txt, text4_tweet.txt} which are respectively the full tweet data, ['0']group tweet, and ['4']group tweet
    * <TOFILE> is the name of the output file
* When textrank project is transferred to HPC, just change the <TOFILE> and <FROMFILE> in run.sh, then ``sbatch run.sh``, you're ready to go