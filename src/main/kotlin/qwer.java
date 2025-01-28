import javafx.print.Printer;

import java.util.ArrayList;
import java.util.List;

public class qwer {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        System.out.print(taskManager.manageTasks("ADD,ПосетитьВстречу,2;REMOVE;ADD,ПрочитатьГазету,1;REMOVE;GET"));
    }
    static class TaskManager {
        List<Zadacha> listZ = new ArrayList<>();
        public String manageTasks(String input) {

            return getListInt(input);
        }
        public String getListInt(String str) {
            String listInt ="";
            String one=""; int x=0,x_0,n=0;
            boolean on =true;
            for (int i =0; i<str.length();i++) {
                if (str.charAt(i)==';') {
                    try {
                        if (one.startsWith("ADD")) {
                            int n1 = one.indexOf(','),n2 = one.indexOf(',',n1+1);
                            ADD(one.substring(n1+1,n2),Integer.parseInt(one.substring(n2+1)));
                            sort();
                        }
                        else if (one.startsWith("REMOVE")) {
                            REMOVE();
                        }
                    }
                    catch (Exception e) {}
                    n++;
                    one ="";
                }else {one+=str.charAt(i);}
            }
            return GET();
        }
        public void ADD(String z0, int p0) {
            Zadacha zOb = new Zadacha(z0,p0);
            listZ.add(zOb);
        }
        public  void REMOVE() {
            if (listZ.size()>0) {
                int iZ = REMOVE_SORT();
                listZ.remove(0);}
        }
        public String GET() {
            String str="";
            sortA();
            for(Zadacha z: listZ) {
                str+=z.getZ()+","+z.getP()+";";
            }
            if (str.length()>0) {str=str.substring(0,str.length()-1);}
            else {str="Список пуст";}
            return str;
        }
        public void sort() {
            for (int i=0;i< listZ.size()-1;i++) {
                int min = i;
                for (int j=i+1;j<listZ.size();j++) {
                    if (listZ.get(j).getP()< listZ.get(min).getP()) {
                        min = j;
                    }
                }
                Zadacha buf = listZ.get(i);
                listZ.set(i,listZ.get(min));
                listZ.set(min,buf);
            }
        }
        public void sortA() {
            System.out.println(""+listZ.size());
            for (int i=1; i<=5;i++) {
                int begin=-1,end=-1; boolean r=false;
                for (int j=0;j<listZ.size();j++) {
                    if (!r&&listZ.get(j).getP()==i) {r=true;begin =j;}
                    else if (r&&listZ.get(j).getP()!=i) {r=false;end =j-1;}
                    if (r&&j==listZ.size()-1) {r=false;end =j;}
                    if (begin!=-1&&end!=-1&&end-begin>0) {
                        for (int m=begin;m<end;m++) {
                            int min = m;
                            for (int n=begin+1;n<=end;n++) {
                                if (listZ.get(min).getZ().compareTo(listZ.get(n).getZ())>0) {
                                    min = n;
                                }
                            }
                            Zadacha buf = listZ.get(m);
                            listZ.set(m,listZ.get(min));
                            listZ.set(min,buf);
                        }
                    }
                    System.out.println(""+begin+" "+end);
                }

            }
        }

        public int REMOVE_SORT() {
            int iZ=-1;
            if (listZ.size()>0) {
                int pMax = listZ.get(0).getP(); iZ=0;
                for (int i=0; i<listZ.size();i++) {
                    if (listZ.get(i).getP()!=pMax) {break;}
                    else {
                        if (listZ.get(iZ).getZ().compareTo(listZ.get(i).getZ())>0) {iZ = i;}
                    }
                }
            }
            return iZ;
        }
    }
    static class Zadacha {
        private String z; int p;
        Zadacha(String z0,int p0) {
         z=z0; p=p0;
        }
        public String getZ() {return z;}
        public int getP() {return p;}
    }
}

