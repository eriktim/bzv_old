<?php

class VotePeriod extends Base {

  const TABLE_NAME = 'bzv_vote_periods';
  const COLUMN_NAME = 'vote_periodid';

  private function _day_as_text($time) {
    $i = date('N', $time);
    $days = array('zondag', 'maandag', 'dinsdag', 'woensdag',
        'donderdag', 'vrijdag', 'zaterdag', 'zondag');
    return $days[$i];
  }

  private function _month_as_text($time) {
    $i = date('n', $time);
    $months = array('', 'januari', 'februari', 'maart', 'april', 'mei', 'juni',
        'july', 'augustus', 'september', 'oktober', 'november', 'december');
    return $months[$i];
  }

  public function get_vote_count() {
    return (int) parent::get_value('vote_count');
  }

  public function get_vote_count_as_text() {
    $i = $this->get_vote_count();
    $numbers = array('geen', 'één', 'twee', 'drie', 'vier', 'vijf');
    if ($i > count($numbers)) {
      return (string)$i;
    }
    return $numbers[$i];
  }

  public function get_date_end() {
    return strtotime(parent::get_value('date_end'));
  }

  public function get_date_reference() {
    return strtotime(parent::get_value('date_reference'));
  }

  public function get_end_date() {
    $time = $this->get_date_end();
    return $this->_day_as_text($time)
        . date(' j ', $time)
        . $this->_month_as_text($time)
        . date(', G:i', $time);
  }

  public function as_html() {
    return '<div class="peasant pea' . $this->id . '">'
        . '<div class="icon ico-warning"></div></div>';
  }

  public static function get_current() {
    $data = array('year' => date('Y') - 1);
    $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year AND NOW() BETWEEN date_start AND date_end ORDER BY id';

    $periods = parent::fetch_array($q, $data);
    if (count($periods) != 1) {
      return NULL;
    }
    return $periods[0];
  }

  public static function get_last() {
    $data = array('year' => date('Y') - 1);
    $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id DESC LIMIT 1';

    $periods = parent::fetch_array($q, $data);
    if (count($periods) > 0) {
      return $periods[0];
    }
    return NULL;
  }

  public static function get_all($year) {
    $data = array('year' => $year);
    $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id';

    return parent::fetch_array($q, $data);
  }

}
