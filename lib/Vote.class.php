<?php

class Vote extends Base {

  const TABLE_NAME = 'bzv_votes';
  const COLUMN_NAME = 'voteid';

  public function get_candidate() {
    return parent::get_object(Candidate::c());
  }

  public function get_peasant() {
    $candidate = $this->get_candidate();
    if (!$candidate) {
      return NULL;
    }
    return $candidate->get_peasant();
  }

  public function get_period() {
    return parent::get_object(VotePeriod::c());
  }

  public function get_type() {
    return parent::get_object(VoteType::c());
  }

  public static function get_by_user_in_period($user, $period) {
    $data = array(
      User::COLUMN_NAME => $user->id,
      VotePeriod::COLUMN_NAME => $period->id
    );
    $q = 'SELECT id FROM ' . self::TABLE_NAME
        . ' WHERE ' . User::COLUMN_NAME . '=:' . User::COLUMN_NAME
        . ' AND ' . VotePeriod::COLUMN_NAME . '=:' . VotePeriod::COLUMN_NAME
        . ' ORDER BY id';

    return parent::fetch_array($q, $data);
  }

  public static function get_all($year) {
    $data = array('year' => $year);
    $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id';

    return parent::fetch_array($q, $data);
  }
  
}
