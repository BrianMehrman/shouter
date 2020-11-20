# Shouter

 A small Twitter clone that lets users enter in “shouts” which are stored in a PostgreSQL database and displayed on the front page of the app.


## Install dependencies
```
brew install skaffold
brew install kubernetes-cli
brew install kustomize
brew install k9s
```

## Build Docker image

```
docker build -t shouter:0.0.1 . -f ./kubernetes/Dockerfile
```

If you build your image remember to update the `kubernetes/shouter/deployment.yaml` with the image name.

## Starting K8s

```
skaffold dev --port-forward
```

## Usage

Open `http://localhost:9303`.
