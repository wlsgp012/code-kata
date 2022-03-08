(ns dojo.design-pattern.05-template-method)

(defn cast-spell
  [spell enemy] (cast-spell spell)
  [spell] (println spell))

(defn chest? [location] (:chest location))
(defn enemies? [location] (:enemies location))

(defn mage-handle-chest [chest])

(defn mage-attack [enemies]
  (if (> (count enemies) 10)
    (do (cast-spell "Freeze Nova")
        (cast-spell "Teleport"))
    (doseq [e enemies]
      (cast-spell "Fireball" e))))

(defn move-to [character location & {:keys [handle-chest attack]
                                     :or {handle-chest (fn [chest])
                                          attack (fn [enemies] (println "run away"))}}]
  (cond
    (chest? location) (handle-chest (:chest location))
    (enemies? location) (attack (:enemies location)))
  (move-to character (:next-location location)))

(defn mage-move [character location]
  (move-to character location :handle-chest mage-handle-chest :attack mage-attack))

;; or use multimethod
