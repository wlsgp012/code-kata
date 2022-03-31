(ns dojo.design-pattern.20-adapter)

(defn attack-with-bow [])
(defn attack-with-sword [])
(defn block-with-shield [])

{:name "Lancelot"
 :speed 1.0
 :attack-bow-fn attack-with-bow
 :attack-sword-fn attack-with-sword
 :block-fn block-with-shield}

(defn throw-grenade [g] (println (str "Fire in the hole!" g)))
(defn shot [gun] (println (str "Tang!" gun)))


{:name "Commando"
 :speed 5.0
 :attack-bow-fn (partial throw-grenade "F1")
 :attack-sword-fn (partial shot "M16")
 :block-fn nil}
