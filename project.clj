(defproject clj-vaadin "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0-beta1"]
                 [org.clojure/algo.monads "0.1.0-SNAPSHOT"]
                 [core.logic "0.6.1-SNAPSHOT"]
                 [org.clojure/java.data "0.0.1-SNAPSHOT"]
                 [hafni "1.0.5-SNAPSHOT"]
                 [com.vaadin/vaadin "6.6.2"]
                 [org.eclipse.jetty/jetty-server "8.0.0.M3"]
                 [org.eclipse.jetty/jetty-webapp "8.0.0.M3"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     [org.clojure/clojure "1.3.0-beta1" :classifier "sources"]]
  :aot [vaadin.app vaadin.servlet]
  :main vaadin.jetty)
