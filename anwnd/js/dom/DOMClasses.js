/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define([], function() {
  var DOMClasses = function() {
    var
      CLASS_TEMPLATE = 'template',
      CLASS_WRAPPER_ITEM = 'wrapper_item',
      CLASS_FOLDER = 'folder',
      CLASS_FOLD_AREA = 'fold_area',
      CLASS_FOLDER_TITLE = 'folder_title',
      CLASS_ITEM = 'item',
      CLASS_FOLD_FLAG = 'fold',
      CLASS_MESSAGE_WAIT = 'waiting';

    this.getTemplate = function() {
      return CLASS_TEMPLATE;
    };
    this.getWrapperItem = function() {
      return CLASS_WRAPPER_ITEM;
    };
    this.getFolder = function() {
      return CLASS_FOLDER;
    };
    this.getFoldArea = function() {
      return CLASS_FOLD_AREA;
    };
    this.getFolderTitle = function() {
      return CLASS_FOLDER_TITLE;
    };
    this.getItem = function() {
      return CLASS_ITEM;
    };
    this.getFoldFlag = function() {
      return CLASS_FOLD_FLAG;
    };
    this.getWaiting = function() {
      return CLASS_MESSAGE_WAIT;
    };
  };
  return DOMClasses;
});