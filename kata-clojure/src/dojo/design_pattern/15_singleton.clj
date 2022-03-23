(ns dojo.design-pattern.15-singleton)

(def ui-config (load-config "ui.config"))

(defn load-config
  [config-file]
  {:bg-style "black" :font-style "Arial"})

;; or use atom
