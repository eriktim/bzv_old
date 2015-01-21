<?php

class Base {

  const TABLE_NAME = 'override';
  const COLUMN_NAME = 'override';

  public $id = NULL;
  private $_data = NULL;

  function __construct($id) {
    $this->id = (int) $id;
  }

  private function _get_data() {
    global $db;

    $data = array('id' => $this->id);

    $st = $db->prepare('SELECT * FROM ' . static::TABLE_NAME . ' WHERE id=:id');
    $st->execute($data);

    $st->setFetchMode(PDO::FETCH_ASSOC);
    $this->_data = $st->fetch();
  }

  protected function get_value($column) {
    if (!$this->_data) {
      $this->_get_data();
    }
    return $this->_data[$column];
  }

  protected function get_object($class) {
    $value = $this->get_value($class::COLUMN_NAME);
    return new $class($value);
  }

  protected static function fetch_array($q, $data) {
    global $db;

    $objects = array();
    $class = get_called_class();

    $st = $db->prepare($q);
    $st->execute($data);

    $st->setFetchMode(PDO::FETCH_ASSOC);
    while($row = $st->fetch()) {
      $objects[] = new $class($row['id']);
    }

    return $objects;
  }

  public static function c() {
    return get_called_class();
  }

}
