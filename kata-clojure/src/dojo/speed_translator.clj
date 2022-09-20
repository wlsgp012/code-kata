(ns dojo.speed-translator)

(defn km_h->100m [speed]
  ;;(double (/ 100 (/ (* speed 1000) 3600))))
  (double (/ (* 3600 100) (* speed 1000))))

(defn s100m->km_h [speed]
  ;;(double (* (/ 100 speed) (/ 3600 1000))))
  (double (/ (* 3600 100) (* speed 1000))))
