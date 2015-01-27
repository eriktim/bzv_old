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
    $code = $_POST['code'];

    if ($code) {
      $USER = User::login(2014, $code);
    }
  }

  if ($USER) {
    $_SESSION['userid'] = $USER->id;
  } else {
    session_unset();

    echo '<div class="center login">';
    echo '<form method="POST">';
    echo '<input name="code" type="password" value="" readonly/>';
    echo '<ul class="numpad">';
    for ($i = 1; $i <= 9; $i++) {
      echo '<li>' . $i . '</li>';
    }
    echo '<li></li>';
    echo '<li>0</li>';
    echo '<li></li>';
    echo '</ul>';
    echo '</form>';
    echo '</div>';
  }

} else {
  $userid = (int) $_SESSION['userid'];
  $USER = new User($userid);
}

