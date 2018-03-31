public class Record {
    private String className;
    private String predictedClassName;
    private int[] attributes;
    private boolean isUnknown;

    public boolean isUnknown() {
        return isUnknown;
    }

    public void setUnknown(boolean unknown) {
        isUnknown = unknown;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPredictedClassName() {
        return predictedClassName;
    }

    public void setPredictedClassName(String predictedClassName) {
        this.predictedClassName = predictedClassName;
    }

    public int[] getAttributes() {
        return attributes;
    }

    public void setAttributes(int[] attributes) {
        this.attributes = attributes;
    }
}