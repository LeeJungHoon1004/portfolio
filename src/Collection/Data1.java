package Collection;

import java.io.Serializable;

public class Data1 implements Serializable
{
    private int a;
    private int b;
 
    public Data1()
    { }
 
    public Data1(int a, int b)
    {
        this.a=a;
        this.b=b;
    }
 
    public int geta()
    {
        return this.a;
    }
    public void seta(int a)
    {
        this.a=a;
    }
 
 
    public int getb()
    {
        return this.b;
    }
 
    public void setb()
    {
        this.b=b;
    }
 
}