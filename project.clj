(defproject lakewoodpca "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;:plugins [[cider/cider-nrepl "0.10.0-SNAPSHOT"]]
  :dependencies [
                 [org.clojure/clojure "1.7.0"]
                 [hiccup "1.0.5"]
                 [hiccup-bridge "1.0.1"]
                 ]
  :main ^:skip-aot lakewoodpca.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
