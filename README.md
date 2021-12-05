# Systemprogramming

프로젝트명 
-------------

    테트리스 온라인

프로젝트 멤버 이름 및 멤버 별 파트
-------------

    * 황석환: 테트리스 그래픽과 알고리즘 코드 담당
    * 김민철: 리눅스로 실행시키는 서버 코드와 테트리스의 서버 소켓 담당 및 발표

프로젝트 소개 및 개발 내용 소개
-------------

    먼저 테트리스는 자바로 구현하였고, 서버는 리눅스로 구축하였으며 서버가 켜져 있을 때 
    테트리스를 실행시키면 서버에 바로 연결이 됩니다. 메인 화면에 서버에서 최고 점수로 
    등록되어 있는 점수를 출력하게 하였고, 게임 화면에서 서버에 등록되어 있는 1등, 2등, 3등 점수를 출력하도록 
    만들었으며, 사용자가 게임오버 되었을 때 점수를 서버에 보내고 서버에 등록된 점수보다 높은지 비교한 뒤 갱신합니다.

프로젝트 개발 결과물 소개
-------------

#### 다이어그램

  ![123](https://user-images.githubusercontent.com/94677219/144748161-0e9820a8-3e02-464c-b364-c721af22e010.png)
  
    이 프로그램은 일반 PC/노트북 (Window, Linux), 가상 머신 에서도 작동이 가능하며 java 언어로 소스코드를 제작했기 때문에 
    jvm 을 통해 어느 환경에서도 구축을 한다면 작동이 가능합니다. 서버는 Linux 에서 구축하였습니다. 
    테트리스를 부담없이 즐기기 위해 기존 테트리스에서 간편화 했고 온라인 랭킹 시스템을 도입하여 자신의 실력을 다른 유저와
    비교할 수 있도록 만들었습니다.
  
개발 결과물 사용법
--------------

  
    사용자가 접속하면 바로 서버에 접속되는 동시에 최고 점수에 대한 정보를 요청하고 서버에서는 
    서버에 등록된 제일 높은 점수를 반환해주며, 클라이언트는 메인 화면에 점수를 출력합니다.
  
  ![main](https://user-images.githubusercontent.com/94677219/144749248-a6fc6b93-a821-4119-abe6-b587bd2c010d.png)

    떨어지는 블록을 차례로 쌓아 한 줄을 채우도록 합니다.
    
  ![play](https://user-images.githubusercontent.com/94677219/144749312-7c2e9ca2-2089-4b3e-bc73-5522d5bad90e.png)
  
    블록이 일정 높이를 초과하면 게임오버가 되며 게임오버시 클라이언트는 점수를 서버로 전송합니다.
    
  ![gameover](https://user-images.githubusercontent.com/94677219/144749379-fcf08adf-86b0-471a-bc5a-6d3a9bb50270.png)
  
    받은 점수가 서버에 등록된 점수보다 높을 경우 서버는 새롭게 랭킹을 갱신합니다.
    
  ![newscore](https://user-images.githubusercontent.com/94677219/144749414-6456ea6a-1b84-4b90-833b-ccf7d8454b4d.png)
  
 개발 결과물의 필요성 및 활용방안
 --------------
 
    요즘 시국에 집에서 다른 게임처럼 서로 욕할 필요도 없고 가볍게 즐길 수 있는 게임에 대한 필요성을 느꼈고
    허용된 사람만 사용할 서버를 열어 테트리스 뿐만 아니라 더 다양한 게임을 추가하는 식으로도 활용할 수 있습니다.
