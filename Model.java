import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Model implements ProgrammFunction{

     @Override
    public void playLottery(){
        Random random=new Random();
        double forIdUser=random.nextDouble()*1563;

        int idUser=(int)forIdUser;

        String str;
        double totalDropRate=0;
        try {
            File path2=new File("PRIZE-TOY.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(path2,true));

            File path=new File("TOYWAREHOUSE.txt");
            BufferedReader br=new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray =new ArrayList<>();
            while ((str=br.readLine())!=null){
                priorityArray.add(str);
            }


            for (int j=0;j<priorityArray.size();j++){
                String[] secondArray=new String[4];
                secondArray=priorityArray.get(j).split(";");

                if (Integer.parseInt(secondArray[3])==0){
                    priorityArray.remove(j);
                }
                totalDropRate+=Double.parseDouble(secondArray[2]);
            }

            double randomValue=random.nextDouble()*totalDropRate;
            System.out.println(randomValue);
            double currentSum=0;

            for (int k=0;k<priorityArray.size();k++){
                String[] secondArray=new String[4];
                secondArray=priorityArray.get(k).split(";");
                currentSum+=Double.parseDouble(secondArray[2]);

                if (Integer.parseInt(secondArray[3])!=0){
                    if (randomValue<=currentSum){

                        priorityArray.set(k,
                                (secondArray[0]+";"
                                        +secondArray[1]+";"
                                        +(Integer.parseInt(secondArray[2])-1)+";"
                                        +secondArray[3]));

                        JOptionPane.showMessageDialog(null,
                                "Вы выйграли: "+secondArray[1]+"\nваш код выйгрыша: "+idUser,
                                "Окно выйгрыша",
                                JOptionPane.INFORMATION_MESSAGE);

                        bw.write(secondArray[0]+";"+secondArray[1]+";"+idUser);

                        bw.newLine();

                        bw.close();

                        br.close();

                        break;


                    }
                }
            }

            BufferedWriter bwp=new BufferedWriter(new FileWriter(path));

            String replace="";

            for(int p=0;p<priorityArray.size();p++) {
                replace += priorityArray.get(p) + "\n";
            }

            System.out.println(replace);
            bwp.write(replace);
            bwp.close();

        }
        catch (IOException e){
            System.out.println(e);
        }



    }


    @Override
    public void changeWightChance() {
    }

    @Override
    public void addToy() {

        Toy toy=new Toy(Integer.parseInt(JOptionPane.showInputDialog(null,
                "Введите id: ",
                "Добавление игрушки",
                JOptionPane.QUESTION_MESSAGE)),
                JOptionPane.showInputDialog(null,
                        "Введите наименование: ",
                        "Добавление игрушки",
                        JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Введите количество:",
                        "Добавление игрушек",
                        JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Введите вес в %: ",
                        "Добавление игрушки",
                        JOptionPane.QUESTION_MESSAGE)));
        try {
            File path=new File("TOYWAREHOUSE.txt");

            if (!path.exists()){
                path.createNewFile();
            }

            BufferedWriter bw=new BufferedWriter(new FileWriter(path,true));

            bw.write(toy.getAllInfo());

            bw.newLine();

            JOptionPane.showMessageDialog(null,
                    "Игрушка: \nid: "
                            +toy.getId()+"\nназвание: "
                            +toy.getToyName()+"\nколичество: "
                            +toy.getCountOfToy()+"\nвес: "
                            +toy.getWeigthChance()+"\nуспешно добавлена",
                    "Сообщение",
                    JOptionPane.INFORMATION_MESSAGE);

            bw.close();
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null,e,"Ошибка",JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void getToy(){

    }
}
