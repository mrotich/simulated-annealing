package matchmaker;

public interface Room{

    public String[] getValidTypes();

    public String getName();
    public void setName(String value);
    public int getSize();
    public void setSize(int value);
    public String getType();
    public void setType(String value);
    public boolean hasPC();
    public void setPC(boolean value);
    public boolean hasDVD();
    public void setDVD(boolean value);
    public boolean hasVCR();
    public void setVCR(boolean value);
    public boolean hasOH();
    public void setOH(boolean value);
    public int getSlides();
    public void setSlides(int value);

}
