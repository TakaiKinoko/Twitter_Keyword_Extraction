#!/bin/bash
#SBATCH --nodes=2
#SBATCH --ntasks-per-node=4
#SBATCH --cpus-per-task=1
#SBATCH --time=1:00:00
#SBATCH --mem=20GB
#SBATCH --job-name=myTest
#SBATCH --mail-type=END
#SBATCH --mail-user=fh643@nyu.edu
#SBATCH --output=slurm_%j.out

module purge

SRCDIR=$HOME/hw2_sentiment
RUNDIR=$SCRATCH/my_project/run-${SLURM_JOB_ID/.*}
mkdir -p $RUNDIR

cd $SLURM_SUBMIT_DIR

module load maven/3.5.2
mvn compile
mvn exec:java -Dexec.mainClass="Analysis"

