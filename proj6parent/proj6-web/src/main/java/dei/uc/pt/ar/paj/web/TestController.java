package dei.uc.pt.ar.paj.web;

import java.io.Serializable;

public class TestController implements Serializable
{
   private static final long serialVersionUID = 7028608421091861830L;

   private String test;

   public TestController()
   {
      test = "abc";
   }

   public void testMethod()
   {
      test = "cba";
   }

   public String getTest()
   {
      return test;
   }
}