public class SingleResponsiblity{

    public static void main (String args[]){
        // 测试方法一
        Verhicle verhicle = new Verhicle();
        verhicle.run("汽车");
        verhicle.run("轮船");
        verhicle.run("飞机");

        // 测试方法二
        VerhicleAir verhicleAir = new VerhicleAir();
        verhicleAir.run("飞机");

        VerhicleRoad verhicleRoad = new VerhicleRoad();
        verhicleRoad.run("汽车");
        
        VerhicleSea verhicleSea = new VerhicleSea();
        verhicleSea.run("轮船");
        
        // 测试方法三
        Verhicle2 verhicle2 = new Verhicle2();
        verhicle2.runRoad("汽车");
        verhicle2.runSea("轮船");
        verhicle2.runAir("飞机");

    }

}

// 方式一
class Verhicle{
    public void run(String verhicleName){
        System.out.println(verhicleName + "在路上跑");

    }

}
// 方式二
class VerhicleAir{
    public void run(String verhicleName){
        System.out.println(verhicleName + "在空中飞");
    }
}
class VerhicleRoad{
    public void run(String verhicleName){
        System.out.println(verhicleName + "在路上跑");
    }
}
class VerhicleSea{
    public void run(String verhicleName){
        System.out.println(verhicleName + "在海里游");
    }
}

// 方式三
class Verhicle2{

    public void runRoad(String verhicleName){
        System.out.println(verhicleName + "在路上跑");

    } public void runSea(String verhicleName){
        System.out.println(verhicleName + "在海里游");

    } public void runAir(String verhicleName){
        System.out.println(verhicleName + "在空中飞");

    }
}

