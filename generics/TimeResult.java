package generics;

public class TimeResult {

    public double[] add;
    public double[] remove;
    public double[] contains;
    public int size;

    public TimeResult(int size){
        this.size = size;
        add = new double[size];
        remove = new double[size];
        contains = new double[size];
    }

    public double middleTime(double[] comp){
        double dataMiddle = 0;
        for (int i = 0; i < size; i++){
            dataMiddle += comp[i];
        }
        return dataMiddle/size;
    }

    public double sigma(double dataMiddle, double[] comp){ // suma(srednia kwadratowa - srednia arytm)/size
        double dataSigma = 0;
        for (int i = 0; i < size; i++) {
            dataSigma += Math.pow(comp[i] - dataMiddle, 2);
        }

        return Math.sqrt(dataSigma/size);
    }

    public void show() {
        double addMiddle = middleTime(add);
        double removeMiddle = middleTime(remove);
        double containsMiddle = middleTime(contains);

        double addSigma = sigma(addMiddle, add);
        double removeSigma = sigma(removeMiddle, remove);
        double containsSigma = sigma(containsMiddle, contains);

        System.out.format("\t\tadd() time: %.6f s +/- %.6f s", addMiddle,  addSigma);
        System.out.format("\t\tcontains() time: %.6f s +/- %.6f s", containsMiddle, containsSigma);
        System.out.format("\t\tremove time: %.6f s +/- %.6f s\n", removeMiddle, removeSigma);
    }
}
