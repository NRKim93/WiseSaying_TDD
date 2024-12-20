/*
 * This source file was generated by the Gradle 'init' task
 */
package wisesaying_tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import wisesaying_tdd.wisesaying.Controller.WiseSayingController;

public class App {
    private final Scanner sc;
    private final WiseSayingController controller;
    private int id;
    private List<WiseSaying> appList;

    public App(Scanner sc) {
        this.sc = sc;
        this.controller = new WiseSayingController(sc);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        System.out.print("모드 선택 : ");
        String modeSelect = sc.nextLine();
        System.out.println(modeSelect + " 모드로 진행합니다. ");
        
        while (true) {
            System.out.print("명령 ) ");
            String cmd = sc.nextLine();
            
            if(cmd.trim().equals("종료")) {
                break;
            } else if (cmd.trim().equals("등록")) {
                id = controller.GetId(modeSelect); 
                int lastId = ++id;
                System.out.print("명언 : ");
                String wiseSaying = sc.nextLine();
                System.out.print("작가 : ");
                String authur = sc.nextLine();
                String addResultMsg = controller.AddWiseSaying(modeSelect,lastId,wiseSaying,authur);

                if (addResultMsg.equals("addSuccess")) {
                    System.out.println(lastId + "번 명언이 등록되었습니다. ");
                }
            } else if (cmd.trim().equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                appList = new ArrayList<>();
                appList = controller.ShowWiseSaying(modeSelect);
                for (WiseSaying ws : appList) {
                    System.out.println(ws.getId() + " / " + ws.getAuthor() + " / " + ws.getWiseSaying());
                }
            } else if (cmd.trim().equals("삭제")) {
                System.out.print("삭제?id=");
                int targerId = Integer.parseInt(sc.nextLine());
                
                String delResultMsg = controller.DeleteWiseSaying(modeSelect,targerId);

                if(delResultMsg.equals("deleteSuccess")) {
                    System.out.println(targerId + "번 명언이 삭제되었습니다. ");
                } else if (delResultMsg.equals("deleteFail")) {
                    System.out.println(targerId + "번 명언 삭제 실패");
                } else {
                    System.out.println(targerId + "번 명언이 존재하지 않습니다. ");
                }
            } else if (cmd.trim().equals("빌드")) {
                String buildResult = "";
                buildResult = controller.BuildWiseSaying(modeSelect);

                if (buildResult.equals("buildSuccess")) {
                    System.out.println("data.json 파일의 내용이 갱신되었습니다.");
                }
            } else if (cmd.trim().equals("수정")) {
                System.out.print("수정?id=");
                int targerId = Integer.parseInt(sc.nextLine());
                appList = controller.TargetRead(cmd, targerId);
                if( appList != null ) {
                    System.out.println("명언(기존) : " + appList.get(0).getWiseSaying());
                    System.out.print("명언 : ");
                    String wiseSaying = sc.nextLine();
                    System.out.println("작가(기존) : " + appList.get(0).getAuthor());
                    System.out.print("작가 : ");
                    String authur = sc.nextLine();
                    String editMsg = controller.EditWiseSaying(cmd, targerId,wiseSaying,authur);

                    if(editMsg.equals("editSuccess")){
                        System.out.println(targerId + "번 명언의 수정이 완료되었습니다.");
    
                    } else {
                        System.out.println(targerId + "번 명언이 존재하지 않습니다. ");
                    }
                } else {
                    System.out.println(targerId + "번 명언이 존재하지 않습니다. ");
                }
            } //
        }
    }
}
