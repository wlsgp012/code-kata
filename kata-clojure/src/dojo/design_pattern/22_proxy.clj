(ns dojo.design-pattern.22-proxy)

;; interface
(defprotocol IBar
  (make-drink [this drink]))

;; Bart's implementation
(deftype StandardBar []
  IBar
  (make-drink [this drink]
    (println "Making drink " drink)
    :ok))

;; our implementation
(deftype ProxiedBar [db ibar]
  IBar
  (make-drink [this drink]
    (make-drink ibar drink)
    (print "substract ingredients from " drink " with " db)))

(def drink {:name "Manhattan"
            :ingredients [["Bourbon" 75] ["Sweet Vermouth" 25] ["Angostura" 5]]})

;; this how it was before
(make-drink (StandardBar.)
            drink)

;; this how it becomes now
(make-drink (ProxiedBar. {:db 1} (StandardBar.))
            drink)
