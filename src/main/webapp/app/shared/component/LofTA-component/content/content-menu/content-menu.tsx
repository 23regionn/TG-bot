import './content-menu.scss';
import React, { useState } from 'react';

import TreeNode from 'primereact/treenode';
import { Tree } from 'primereact/tree';

export interface ContentMenuProps {
  value: TreeNode[];
  onSelect: any;
  loading: any;
  filter?: boolean | false;
}

export const ContentMenu = (props: ContentMenuProps) => {
  const [expandedKeys, setExpandedKeys] = useState({});
  const [selectedNodeKey, setSelectedNodeKey] = useState(null);

  const onToggle = event => {
    setExpandedKeys(event.value);
  };

  const onSelectionChange = event => {
    setSelectedNodeKey(event.value);
  };

  const onSelection = event => {
    props.onSelect(event.node);
  };

  return (
    <Tree
      className="leftBar-tree-menu"
      value={props.value}
      selectionMode="single"
      selectionKeys={selectedNodeKey}
      onSelect={onSelection}
      onSelectionChange={onSelectionChange}
      onToggle={onToggle}
      expandedKeys={expandedKeys}
      filter={props.filter}
      loading={props.loading}
    ></Tree>
  );
};
