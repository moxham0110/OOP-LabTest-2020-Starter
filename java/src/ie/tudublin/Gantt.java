package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table t = loadTable("tasks.csv", "header");
		for(TableRow tr:t.rows())
		{
			Task tk = new Task(tr);
			tasks.add(tk);
		}
	}

	public void printTasks()
	{
		for(Task tk:tasks)
		{
			println(tk);
		}
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}

	public void displayTasks(){
		float border = width * 0.05f;

		stroke(0, 0, 255);
		textAlign(CENTER, CENTER);

		for(int i = 1; i <= 30; i++){
			float x = map(i, 1, 30, 150, 800 - border);
			line(x, border, x, height - border);
			text(i, x, border/2);
		}

		for(int i = 0; i < 9; i++){
			
			Task myTask = new Task();
			myTask = tasks.get(i);

			float x = map(i, 0, 9, 100, 500);
			text(myTask.getTask(), border*2, x);


			float j = map(myTask.getStart(), 1, 30, 150, width - border);
			float k = map(myTask.getEnd(), 1, 30, 150, width - border);
			
			rect(j, x - 10, k-j, 25);
			
			
		}

	}
}
