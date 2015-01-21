<?php

spl_autoload_register(function ($class) {
    include '../lib/' . $class . '.class.php';
});

try {
  $db = new PDO('pgsql:host=localhost;dbname=bzv', 'bzv_user', 'bzv_password');
  $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(PDOException $e) {
  die($e->getMessage());
}
