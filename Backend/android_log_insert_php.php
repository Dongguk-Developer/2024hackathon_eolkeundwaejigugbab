<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('android_log_php.php');

    $android = strpos($_SERVER['HTTP_USER_AGENT'],"Android");

    if (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit']))
    {

        $useremail=$_POST['useremail'];
        $userid=$_POST['userid'];
        $password=$_POST['password'];
        $userphone=$_POST['userphone'];
        $username=$_POST['username'];
        $userdate=$_POST['userdate'];
        $usercreated = date("Y-m-d H:i:s");
        


        if(empty($useremail)){
            $errMSG = "이메일을 입력하세요.";
        }
        else if(empty($userid)){
            $errMSG = "아이디를 입력하세요.";
        }
        else if(empty($password)){
            $errMSG = "비밀번호를 입력하세요.";
        }

        else if(empty($userphone)){
            $errMSG = "핸드폰 번호를 입력하세요.";
        }
        else if(empty($username)){
            $errMSG = "이름을 입력하세요.";
        }
        else if(empty($userdate)){
            $errMSG = "생년월일을 입력하세요.";
        }


        



        if(!isset($errMSG)) //$errMSG가 존재하지 않았을 경우
        {
            try{ //try , catch
                $stmt = $con->prepare('INSERT INTO users(useremail, userid, password ,userphone, username, userdate, usercreated)
                 VALUES(:useremail,:userid, :password, :userphone , :username, :userdate, :usercreated )');
                $stmt->bindParam(':useremail', $useremail);
                $stmt->bindParam(':userid', $userid);
                $stmt->bindParam(':password', $password);
                $stmt->bindParam(':userphone', $userphone);
                $stmt->bindParam(':username', $username);
                $stmt->bindParam(':userdate', $userdate);
                $stmt->bindParam(':usercreated', $usercreated);

                if($stmt->execute())
                {
                    
                    $successMSG = "새로운 사용자를 추가했습니다.";
                    echo "success";
                }
                else
                {
                    $errMSG = "사용자 추가 에러";
                    echo "fail"; 
                }


             
        
            } catch(PDOException $e) { //PHP와 MySQL 연동은 성공하였으나 DB 오류가 났을때
 
       
                
                die("Database error: " . $e->getMessage()); 
            }
        }

    }
?>

<html>
<body>



<?php 

        if (isset($errMSG)) { //만약 $errMSG의 변수의 값이 존재할 경우 
            // 자바 스크립트의 alert를 이용하여 팝업창에 $errMSG를 띄운다.
         echo   "<script>      
         alert('{$errMSG}');
         </script>";         
        }
        
        if (isset($successMSG)){ //만약 $$successMSG 변수의 값이 존재할 경우 
       // 자바 스크립트의 alert를 이용하여 팝업창에 $successMSG 띄운다.
        echo   "<script>      
        alert('{$successMSG}');
        </script>";
        }

        ?>
      


<form action="<?php $_PHP_SELF ?>" method="POST">
                <h1>회원가입 창</h1>
                  <!-- 일반적인 입력 형태 -->
                <p><input type="email" name="useremail" id="useremail" placeholder="E-mail"></p>
                <p><input type="text" name="userid" id="userid" placeholder="ID"></p>
                <p><input type="password" name="password" id="password" placeholder="Password"></p>
                <p><input type="text" name="userphone" id="userphone" placeholder="Phone Number 000-0000-0000"></p>
                <p><input type="text" name="username" id="username" placeholder="Name"></p>
                <p><input type="date" name="userdate" id="userdate" placeholder="Date"></p>


            <input type = "submit" name = "submit" />
        </form>
</body>
</html>

<?php
        
        ?>
