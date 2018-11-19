<?xml version="1.0" encoding="UTF-8"?>

<workflow-app xmlns="uri:oozie:workflow:0.5" name="loadjobs.workflow">
  <start/>
  <action name="map-reduce-1">
    <map-reduce/>
  </action>
  <action name="hive2-1">
    <hive2 xmlns="uri:oozie:hive2-action:0.1"/>
  </action>
  <action name="spark-1">
    <spark xmlns="uri:oozie:spark-action:0.1"/>
  </action>
  <action name="fs-1">
    <fs/>
  </action>
  <end name="end"/>
</workflow-app>
<!--
<workflow>
  <node name="start" x="50" y="64"/>
  <node name="end" x="698" y="102"/>
  <node name="map-reduce-1" x="293" y="170"/>
  <node name="hive2-1" x="411" y="247"/>
  <node name="spark-1" x="540" y="152"/>
  <node name="fs-1" x="170" y="158"/>
</workflow>-->
