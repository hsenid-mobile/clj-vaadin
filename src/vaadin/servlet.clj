(ns vaadin.servlet
  (:gen-class
    :extends com.vaadin.terminal.gwt.server.AbstractApplicationServlet
    :name vaadin.Servlet))
 
(defn ^Class -getApplicationClass [this]
  vaadin.App)
 
(defn  -getNewApplication [this request]
  (vaadin.App.))