<?php

spl_autoload_register(function ($class) {
    include '../lib/' . $class . '.class.php';
});

try {
  $db = new PDO('pgsql:host=localhost;dbname=bzv', 'bzv_user', 'bzv_password');
  $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $e) {
  die($e->getMessage());
}

session_start();

$USER = NULL;

if (empty($_SESSION['userid']) || isset($_POST['logout'])) {
  if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    session_unset();
    session_regenerate_id();

    $email = $_POST['email'];
    $password = $_POST['password'];
    if ($email && $password) {
      $USER = User::login($email, $password);
    }
  }

  if ($USER) {
    $_SESSION['userid'] = $USER->id;
  } else {
    session_unset();

    echo '<form method="POST">';
    echo '<div class="login">';
    echo '<input name="email" placeholder="E-mailadres" type="text">';
    echo '<input name="password" placeholder="Wachtwoord" type="password">';
    echo '<input type="submit" value="Login">';
    echo '</div>';
    echo '</form>';
  }

} else {
  $userid = (int) $_SESSION['userid'];
  $USER = new User($userid);
}

