package ie.tudublin;


import processing.data.TableRow;

public class Task {

    private String task;
    private int start;
    private int end;
    
    public String getTask() {
		return task;
	}
	public void setTask(final String task) {
		this.task = task;
	}
	public int getStart() {
		return start;
	}
	public void setStart(final int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(final int end) {
		this.end = end;
    }
    
    public Task(){

        this("", 0, 0);
    }

    public Task(final String task, final int start, final int end)
    {
        this.task = task;
        this.start = start;
        this.end = end;
    }

    public Task(final TableRow tr)
    {
        this(
            tr.getString("Task"),
            tr.getInt("Start"),
            tr.getInt("End")
            );
    }
    
    public String toString()
    {
        return task + "\t" + start + "\t" + end;
    }
    



  
    
    
}