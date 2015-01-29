<?php

class User extends Base {

  const TABLE_NAME = 'bzv_users';
  const COLUMN_NAME = 'userid';

  public function get_name() {
    return parent::get_value('name');
  }

  public function get_points() {
    $points = 0;
    $periods = VotePeriod::get_all(2014);
    foreach ($periods as $period) {
      $points += $this->get_points_by_period($period);
    }
    return $points;
  }

  public function get_points_by_period($period) {
    $points = 0;
    $votes = Vote::get_by_user_in_period($this, $period);
    foreach ($votes as $vote) {
      $points += $vote->get_points();
    }
    return $points;
  }

  public function get_year() {
    return (int) parent::get_value('year');
  }

  public static function login($year, $code) {
    global $db;

    $data = array('year' => $year);

    $st = $db->prepare('SELECT id, hash FROM ' . self::TABLE_NAME
         . ' WHERE year=:year ORDER BY id ASC');
    $st->execute($data);

    if ($st->rowCount() === 0) {
      // slow down the 'attacker'
      password_verify($password, 'dummy');
    } else {
      $st->setFetchMode(PDO::FETCH_ASSOC);
      while($row = $st->fetch()) {
        $hash = $row['hash'];
        if (password_verify($code, $hash)) {
          return new User($row['id']);
        }
      }
    }
    return false;
  }

  public static function get_all($year) {
    $data = array('year' => $year);
    $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id';

    return parent::fetch_array($q, $data);
  }

}
