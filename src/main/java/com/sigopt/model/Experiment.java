package com.sigopt.model;

import com.sigopt.net.APIMethodCaller;
import com.sigopt.net.BoundObject;
import com.sigopt.net.PaginatedAPIMethodCaller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Experiment extends APIResource {
    public Experiment() {
        super();
    }

    public Experiment(String id) {
        super();
        this.set("id", id);
    }

    public String getId() {
        return (String) this.get("id");
    }

    public String getType() {
        return (String) this.get("type");
    }

    public String getName() {
        return (String) this.get("name");
    }

    public List<Parameter> getParameters() {
        return Utils.mergeIntoList(new ArrayList<Parameter>(), this.get("parameters"), Parameter.class);
    }

    public Metric getMetric() {
        return Utils.mergeInto(new Metric(), this.get("metric"));
    }

    public Progress getProgress() {
        return Utils.mergeInto(new Progress(), this.get("progress"));
    }

    public Map<String, String> getMetadata() {
      return (Map<String, String>) this.get("metadata");
    }

    public String getClient() {
      return (String) this.get("client");
    }

    public String getState() {
      return (String) this.get("state");
    }

    public Integer getCreated() {
      return Utils.asInteger(this.get("created"));
    }

    public static APIMethodCaller<Experiment> fetch() {
        return new APIMethodCaller<Experiment>("get", "/experiments/:id", Experiment.class);
    }

    public static APIMethodCaller<Experiment> fetch(String id) {
        return Experiment.fetch().addPathComponent("id", id);
    }

    public static PaginatedAPIMethodCaller<Experiment> list() {
        return new PaginatedAPIMethodCaller<Experiment>("get", "/experiments", Experiment.class);
    }

    public static APIMethodCaller<Experiment> create() {
        return new APIMethodCaller<Experiment>("post", "/experiments", Experiment.class);
    }

    public static APIMethodCaller<Experiment> create(Experiment e) {
        return Experiment.create().data(e);
    }

    public static APIMethodCaller<Experiment> update() {
        return new APIMethodCaller<Experiment>("put", "/experiments/:id", Experiment.class);
    }

    public static APIMethodCaller<Experiment> update(String id) {
        return Experiment.update().addPathComponent("id", id);
    }

    public static APIMethodCaller<Experiment> update(String id, Experiment e) {
        return Experiment.update(id).data(e);
    }

    public static APIMethodCaller<Experiment> delete() {
        return new APIMethodCaller<Experiment>("delete", "/experiments/:id", Experiment.class);
    }

    public static APIMethodCaller<Experiment> delete(String id) {
        return Experiment.delete().addPathComponent("id", id);
    }

    public static class Subresource<T extends APIObject> extends BoundObject {
        String name;
        Class<T> klass;

        public Subresource(String prefix, String name, Class<T> klass) {
            super(prefix);
            this.name = name;
            this.klass = klass;
        }

        public APIMethodCaller<T> fetch() {
            return new APIMethodCaller<T>("get", this.prefix() + "/" + this.name + "/:id", klass);
        }

        public APIMethodCaller<T> fetch(String id) {
            return this.fetch().addParam("id", id);
        }

        public PaginatedAPIMethodCaller<T> list() {
            return new PaginatedAPIMethodCaller<T>("get", this.prefix() + "/" + this.name, klass);
        }

        public APIMethodCaller<T> create() {
            return new APIMethodCaller<T>("post", this.prefix() + "/" + this.name, klass);
        }

        public APIMethodCaller<T> create(T o) {
            return this.create().data(o);
        }

        public APIMethodCaller<T> update() {
            return new APIMethodCaller<T>("put", this.prefix() + "/" + this.name + "/:id", klass);
        }

        public APIMethodCaller<T> update(String id) {
            return this.update().addPathComponent("id", id);
        }

        public APIMethodCaller<T> update(String id, T o) {
            return this.update(id).data(o);
        }

        public APIMethodCaller<VoidObject> deleteList() {
            return new APIMethodCaller<VoidObject>("delete", this.prefix() + "/" + this.name, null);
        }

        public APIMethodCaller<Experiment> delete() {
            return new APIMethodCaller<Experiment>("delete", this.prefix() + "/" + this.name + "/:id", null);
        }

        public APIMethodCaller<Experiment> delete(String id) {
            return this.delete().addPathComponent("id", id);
        }
    }

    public Subresource<Observation> observations() {
        return new Subresource<Observation>("/experiments/" + this.getId(), "observations", Observation.class);
    }

    public Subresource<Suggestion> suggestions() {
        return new Subresource<Suggestion>("/experiments/" + this.getId(), "suggestions", Suggestion.class);
    }

    public static class Builder {
        Experiment e;

        public Builder() {
            this.e = new Experiment();
        }

        public Experiment build() {
            return this.e;
        }

        public Builder created(Integer created) {
            this.e.set("created", created);
            return this;
        }

        public Builder parameters(List<Parameter> parameters) {
            this.e.set("parameters", parameters);
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.e.set("metadata", metadata);
            return this;
        }

        public Builder metric(Metric metric) {
            this.e.set("metric", metric);
            return this;
        }

        public Builder progress(Progress progress) {
            this.e.set("progress", progress);
            return this;
        }

        public Builder client(String client) {
            this.e.set("client", client);
            return this;
        }

        public Builder id(String id) {
            this.e.set("id", id);
            return this;
        }

        public Builder name(String name) {
            this.e.set("name", name);
            return this;
        }

        public Builder state(String state) {
            this.e.set("state", state);
            return this;
        }

        public Builder type(String type) {
            this.e.set("type", type);
            return this;
        }
    }
}
