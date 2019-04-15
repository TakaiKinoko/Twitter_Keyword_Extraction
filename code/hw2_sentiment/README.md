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
* TextRank project separate from the main one above. 