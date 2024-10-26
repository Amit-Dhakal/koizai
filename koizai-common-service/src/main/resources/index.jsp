<html>
<head>
  <title>KoiZai API Files Monitoring</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
	body{
		padding:1%;
	}
	header {
	  background-color: #666;
	  padding: 10px;
	  text-align: center;
	  font-size: 35px;
	  color: white;
	}
	section {
	  padding: 2%;
	}
  </style>
</head>

<body>
<header>
<h1>KoiZai API Files Monitoring</h1>
<br/>
</header>
<section>
<%@ page import="java.io.*, java.nio.*, java.util.*, java.text.SimpleDateFormat"%>
<%

	String filterName1 = request.getParameter("name1")!=null?request.getParameter("name1"):"";
	String filterName2 = request.getParameter("name2")!=null?request.getParameter("name2"):"";
    String sortedBy1 = request.getParameter("sort1")!=null?request.getParameter("sort1"):"DATE";
	String sortedBy2 = request.getParameter("sort2")!=null?request.getParameter("sort2"):"DATE";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String APP_HOME = System.getProperty("user.dir");
    String base = request.getContextPath() + "/";
    File directory = new File(APP_HOME + "/webapps/koizai_files");
	//File directory = new File("../webapps/koizai_files");

	Comparator<File> dateComparator = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            int result = 0;
            if(o1.lastModified()>o2.lastModified()){
                result = 1;
            }else if(o1.lastModified()< o2.lastModified()){
                result = -1;
            }
            return result;
        }
    };
	Comparator<File> nameComparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result;
            }
        };

%>

<div class="row">
<div class="col-md-6">
<form action = "index.jsp" method = "POST" >
  <div class="form-row">
   <div class="col-md-2">
		Filename
   </div>
   <div class="col-md-10">
	   <input type = "text" name = "name1" value="<%= filterName1 %>"/>
	   <input type="hidden" name="name2" value="<%= filterName2 %>"/>
	   <input type = "submit" value = "Search" />
   </div>
 </div>
 <div class="form-row">
   <div class="col-md-2">
		Sort by
   </div>
   <div class="col-md-10">
		<select name="sort1" id="sortFields" onchange="this.form.submit()">
		  <option value=""></option>
		  <option value="FILENAME">Filename</option>
		  <option value="DATE">Last Modified</option>
		</select>
		<label><%= sortedBy1 %></label>
		<input type="hidden" name="sort2" value="<%= sortedBy2 %>"/>
	</div>
 </div>
</form>

<table class="table table-responsive-md table-bordered table-hover" width="100%">
<thead class="thead-dark">
<tr>
<th>Filename</th>
<th>Last Modified</th>
<th>Size</th>
</tr>
</thead>
<%
    Comparator<File> comp1= dateComparator;
	if(sortedBy1.equals("FILENAME")){
       comp1 = nameComparator;
    }
	File[] arr1 = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".log")){
                    return false;
                }else{
                    return true;
                }

            }
        });
    List<File> list1 = Arrays.asList(arr1);
    Collections.sort(list1, comp1);
    if (list1!=null && list1.size() > 0) {
        int size = list1.size();
        for (int i = 0; i < size; i++) {
            File file = list1.get(i);
            String filename = file.getName();
            Date last = new Date(file.lastModified());
            String strLast = sdf.format(last);
			long fileSizeInBytes = file.length();
			long fileSizeInKB = fileSizeInBytes / 1024;
            if(filename.indexOf(".jsp")>-1){
               continue;
            }
            if(filterName1!=null && filename.indexOf(filterName1)==-1){
               continue;
            }
          %>
              <tr>
                <td><a href="<%=base + filename%>" target="_blank"><%=filename%></a></td>
                <td style="width:20%;text-align:center"><%=strLast%></td>
				<td style="width:10%;text-align:right"><%=fileSizeInKB%> KB</td>
              <tr>
          <%
        }
    }
%>
</table>
</div>
<div class="col-md-6">
<form id="form2" action = "index.jsp" method = "POST" >
 <div class="form-row">
   <div class="col-md-2">
	Filename
   </div>
   <div class="col-md-10">
	<input type = "text" name = "name2" value="<%= filterName2 %>"/>
	<input type="hidden" name="name1" value="<%= filterName1 %>"/>
	<input type = "submit" value = "Search" />

   </div>


 </div>
 <div class="form-row">
   <div class="col-md-2">
	Sort by
   </div>
   <div class="col-md-10">
		<select name="sort2" id="sortFields" onchange="this.form.submit()">
		  <option value=""></option>
		  <option value="FILENAME">Filename</option>
		  <option value="DATE">Last Modified</option>
		</select>
		<label><%= sortedBy2 %></label>
		<input type="hidden" name="sort1" value="<%= sortedBy1 %>"/>
	</div>
 </div>
</form>

<table class="table table-responsive-md table-bordered table-hover" width="100%">
<thead class="thead-dark">
<tr>
<th>Filename</th>
<th>Last Modified</th>
<th>Size</th>
</tr>
</thead>
<tbody>
<%
    Comparator<File> comp2= dateComparator;
	if(sortedBy2.equals("FILENAME")){
       comp2 = nameComparator;
    }
	File[] arr2 = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".log")){
                    return true;
                }else{
                    return false;
                }

            }
        });
    List<File> list2 = Arrays.asList(arr2);
    Collections.sort(list2, comp2);
    if (list2!=null && list2.size() > 0) {
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            File file = list2.get(i);
            String filename = file.getName();
            Date last = new Date(file.lastModified());
            String strLast = sdf.format(last);
			long fileSizeInBytes = file.length();
			long fileSizeInKB = fileSizeInBytes / 1024;
            if(filename.indexOf(".jsp")>-1){
               continue;
            }
            if(filterName2!=null && filename.indexOf(filterName2)==-1){
               continue;
            }
          %>
              <tr>
                <td><a href="<%=base + filename%>" target="_blank"><%=filename%></a></td>
                <td style="width:20%;text-align:center"><%=strLast%></td>
				<td style="width:10%;text-align:right"><%=fileSizeInKB%> KB</td>
              <tr>

          <%
        }
    }
%>
</tbody>
</table>
</div>
</div>
</section>
</body>
</html>