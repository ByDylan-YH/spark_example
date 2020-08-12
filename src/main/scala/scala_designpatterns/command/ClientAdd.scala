package scala_designpatterns.command

/**
  * Author:BY
  * Date:2020/3/7
  * Description:
  */
object ClientAdd extends App {
  val command: AbstractCommand = new AddCommand;
  val form = new CalculatorForm(command);
  form.compute(10);
  form.compute(5);
  form.undo();
  form.undo();
  form.redo();
  form.redo();
  form.redo();
  form.undo();
  form.undo();
  form.undo();
  form.redo();
  form.compute(100);
}