<script setup>
import Panel from "@/components/Panel.vue";
import { useRoute, useRouter } from "vue-router";
import { reactive } from "vue";
import { useFetch } from "@/composables/useFetch";
import { useFlash } from "@/composables/useFlash";

const route = useRoute();
const router = useRouter();

let { flash } = useFlash();

const form = reactive({
  title: "",
  text: "",
});

useFetch(
  `http://localhost:8080/api/v1/todos/${route.params.id}`,
  "GET",
)
    .then((res) => res.json())
    .then(data => {
      form.title = data.title;
      form.text = data.text;
    });

const submit = async () => {
  await useFetch(
    `http://localhost:8080/api/v1/todos/${route.params.id}`,
    "PUT",
    form,
  );

  flash("Updated!", "The task has been successfully updated.");

  router.push("/");
};
</script>

<template>
  <div class="flex-1 container m-auto flex justify-center">
    <div class="max-w-2xl my-auto">
      <RouterLink :to="'/task/' + route.params.id">
        <p class="text-sm text-center font-mono tracking-wide mb-3">Go Back</p>
      </RouterLink>

      <Panel class="w-80 py-8 px-8">
        <template #heading>
          <h2 class="font-semibold text-xl mb-3">Edit Task</h2>
        </template>

        <br />

        <form @submit.prevent="submit">
          <input
            type="text"
            name="title"
            id="title"
            v-model="form.title"
            placeholder="Title"
            class="border rounded-md border-gray-400 p-2 w-full mb-3"
            required
          />

          <textarea
            class="border rounded-md border-gray-400 p-2 w-full"
            name="text"
            id="text"
            v-model="form.text"
            placeholder="Text"
            required
          ></textarea>

          <button
            type="submit"
            class="mt-3 w-full border rounded-lg bg-blue-400 py-2 px-3 font-semibold"
          >
            Update
          </button>
        </form>
      </Panel>
    </div>
  </div>
</template>
