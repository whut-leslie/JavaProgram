package FileSystem;
import java.sql.Timestamp;
public class Doc {
private String ID;
private String creator;
private Timestamp timestamp;
private String description;
private String filename;

public Doc(String ID, String creator, Timestamp timestamp, String description, String filename) {
	super();
	this.ID = ID;                       //文件ID
	this.creator = creator;             //创作者
	this.timestamp = timestamp;         //上传时间
	this.description = description;     //文件描述
	this.filename=filename;             //文件名
}

public String getID() {
	return ID;
}

public void setID(String iD) {
	ID = iD;
}

public String getCreator() {
	return creator;
}

public void setCreator(String creator) {
	this.creator = creator;
}

public Timestamp getTimestamp() {
	return timestamp;
}

public void setTimestamp(Timestamp timestamp) {
	this.timestamp = timestamp;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getFilename() {
	return filename;
}

public void setFilename(String filename) {
	this.filename = filename;
}

}
